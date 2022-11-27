using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Ticket.Api.DAL;
using Ticket.Api.Models;

namespace Ticket.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClientesController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public ClientesController(TicketDbContext context)
        {
            _context = context;
        }

        // GET: api/Clientes
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Clientes>>> GetClientes()
        {
            return await _context.Clientes.ToListAsync();
        }

        // GET: api/Clientes/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Clientes>> GetClientes(int id)
        {
            var clientes = await _context.Clientes.FindAsync(id);

            if (clientes == null)
            {
                return NotFound();
            }

            return clientes;
        }
        // GET: api/Clientes/nombre,clave
        [HttpGet("{nombre},{clave}")]
        public async Task<ActionResult<Clientes>> GetCliente(string nombre, string clave)
        {
            string decryptedClave = "";
            string decryptedNombre = "";

            foreach (var letra in clave)
                decryptedClave += CharacterDecrypter(letra);
            
            foreach (var letra in nombre)
                decryptedNombre += CharacterDecrypter(letra);
            
            var clientes = await _context.Clientes.FirstOrDefaultAsync(c => c.Nombres.Equals(decryptedNombre) && c.Clave.Equals(decryptedClave));

            if (clientes == null)
            {
                return NotFound();
            }

            return clientes;
        }

        // PUT: api/Clientes/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutClientes(int id, Clientes clientes)
        {
            if (id != clientes.ClienteId)
            {
                return BadRequest();
            }

            _context.Entry(clientes).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ClientesExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Clientes
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Clientes>> PostClientes(Clientes clientes)
        {
            _context.Clientes.Add(clientes);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetClientes", new { id = clientes.ClienteId }, clientes);
        }

        // DELETE: api/Clientes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteClientes(int id)
        {
            var clientes = await _context.Clientes.FindAsync(id);
            if (clientes == null)
            {
                return NotFound();
            }

            _context.Clientes.Remove(clientes);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ClientesExists(int id)
        {
            return _context.Clientes.Any(e => e.ClienteId == id);
        }
        private char CharacterDecrypter(char character)
        {
            char result = ' ';
            switch (character)
            {
                case 'e':
                    {
                        result = 'A';
                        break;
                    }
                case 't':
                    {
                        result = 'a';
                        break;
                    }
                case 'q':
                    {
                        result = 'B';
                        break;
                    }
                case 'k':
                    {
                        result = 'b';
                        break;
                    }
                case 'r':
                    {
                        result = 'C';
                        break;
                    }
                case 'M':
                    {
                        result = 'c';
                        break;
                    }
                case 's':
                    {
                        result = 'D';
                        break;
                    }
                case 'j':
                    {
                        result = 'd';
                        break;
                    }
                case 'f':
                    {
                        result = 'E';
                        break;
                    }
                case 'n':
                    {
                        result = 'e';
                        break;
                    }
                case 'g':
                    {
                        result = 'F';
                        break;
                    }
                case 'D':
                    {
                        result = 'f';
                        break;
                    }
                case 'p':
                    {
                        result = 'G';
                        break;
                    }
                case 'S':
                    {
                        result = 'g';
                        break;
                    }
                case 'G':
                    {
                        result = 'H';
                        break;
                    }
                case 'l':
                    {
                        result = 'h';
                        break;
                    }
                case 'F':
                    {
                        result = 'I';
                        break;
                    }
                case 'Q':
                    {
                        result = 'i';
                        break;
                    }
                case 'm':
                    {
                        result = 'J';
                        break;
                    }
                case 'B':
                    {
                        result = 'j';
                        break;
                    }
                case 'V':
                    {
                        result = 'K';
                        break;
                    }
                case 'h':
                    {
                        result = 'k';
                        break;
                    }
                case 'P':
                    {
                        result = 'L';
                        break;
                    }
                case 'A':
                    {
                        result = 'l';
                        break;
                    }
                case 'H':
                    {
                        result = 'M';
                        break;
                    }
                case 'Z':
                    {
                        result = 'm';
                        break;
                    }
                case 'o':
                    {
                        result = 'N';
                        break;
                    }
                case 'v':
                    {
                        result = 'n';
                        break;
                    }
                case 'K':
                    {
                        result = 'O';
                        break;
                    }
                case 'J':
                    {
                        result = 'o';
                        break;
                    }
                case 'b':
                    {
                        result = 'P';
                        break;
                    }
                case 'W':
                    {
                        result = 'p';
                        break;
                    }
                case 'd':
                    {
                        result = 'Q';
                        break;
                    }
                case 'E':
                    {
                        result = 'q';
                        break;
                    }
                case 'a':
                    {
                        result = 'R';
                        break;
                    }
                case 'i':
                    {
                        result = 'r';
                        break;
                    }
                case 'x':
                    {
                        result = 'S';
                        break;
                    }
                case 'O':
                    {
                        result = 's';
                        break;
                    }
                case 'L':
                    {
                        result = 'T';
                        break;
                    }
                case 'w':
                    {
                        result = 't';
                        break;
                    }
                case 'R':
                    {
                        result = 'U';
                        break;
                    }
                case 'T':
                    {
                        result = 'u';
                        break;
                    }
                case 'y':
                    {
                        result = 'V';
                        break;
                    }
                case 'X':
                    {
                        result = 'v';
                        break;
                    }
                case 'N':
                    {
                        result = 'W';
                        break;
                    }
                case 'U':
                    {
                        result = 'w';
                        break;
                    }
                case 'c':
                    {
                        result = 'X';
                        break;
                    }
                case 'I':
                    {
                        result = 'x';
                        break;
                    }
                case 'z':
                    {
                        result = 'Y';
                        break;
                    }
                case 'C':
                    {
                        result = 'y';
                        break;
                    }
                case 'Y':
                    {
                        result = 'Z';
                        break;
                    }
                case 'u':
                    {
                        result = 'z';
                        break;
                    }


                default:
                    {
                        result = character;
                        break;
                    }
            }
            return result;
        }
    }
}
