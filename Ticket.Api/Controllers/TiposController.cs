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
    public class TiposController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public TiposController(TicketDbContext context)
        {
            _context = context;
        }

        // GET: api/Tipos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Tipos>>> GetTipos()
        {
            return await _context.Tipos.ToListAsync();
        }

        // GET: api/Tipos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Tipos>> GetTipos(int id)
        {
            var tipos = await _context.Tipos.FindAsync(id);

            if (tipos == null)
            {
                return NotFound();
            }

            return tipos;
        }

        // PUT: api/Tipos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTipos(int id, Tipos tipos)
        {
            if (id != tipos.TipoId)
            {
                return BadRequest();
            }

            _context.Entry(tipos).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TiposExists(id))
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

        // POST: api/Tipos
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Tipos>> PostTipos(Tipos tipos)
        {
            _context.Tipos.Add(tipos);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTipos", new { id = tipos.TipoId }, tipos);
        }

        // DELETE: api/Tipos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTipos(int id)
        {
            var tipos = await _context.Tipos.FindAsync(id);
            if (tipos == null)
            {
                return NotFound();
            }

            _context.Tipos.Remove(tipos);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool TiposExists(int id)
        {
            return _context.Tipos.Any(e => e.TipoId == id);
        }
    }
}
