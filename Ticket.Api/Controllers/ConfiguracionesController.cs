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
    public class ConfiguracionesController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public ConfiguracionesController(TicketDbContext context)
        {
            _context = context;
        }

        // GET: api/Configuraciones
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Configuraciones>>> GetConfiguraciones()
        {
            return await _context.Configuraciones.ToListAsync();
        }

        // GET: api/Configuraciones/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Configuraciones>> GetConfiguraciones(int id)
        {
            var configuraciones = await _context.Configuraciones.FindAsync(id);

            if (configuraciones == null)
            {
                return NotFound();
            }

            return configuraciones;
        }

        // GET: api/Configuraciones/1,2
        [HttpGet("{theme},{colorIndex}")]
        public async Task<ActionResult<Configuraciones>> GetConfiguracion(int theme, int colorIndex)
        {
            var configuraciones = await _context.Configuraciones.FirstOrDefaultAsync(c => c.Theme==theme && c.ColorSchemeIndex==colorIndex);

            if (configuraciones == null)
            {
                return NotFound();
            }

            return configuraciones;
        }

        // PUT: api/Configuraciones/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutConfiguraciones(int id, Configuraciones configuraciones)
        {
            if (id != configuraciones.ConfiguracionId)
            {
                return BadRequest();
            }

            _context.Entry(configuraciones).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ConfiguracionesExists(id))
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

        // POST: api/Configuraciones
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Configuraciones>> PostConfiguraciones(Configuraciones configuraciones)
        {
            _context.Configuraciones.Add(configuraciones);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetConfiguraciones", new { id = configuraciones.ConfiguracionId }, configuraciones);
        }

        // DELETE: api/Configuraciones/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteConfiguraciones(int id)
        {
            var configuraciones = await _context.Configuraciones.FindAsync(id);
            if (configuraciones == null)
            {
                return NotFound();
            }

            _context.Configuraciones.Remove(configuraciones);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ConfiguracionesExists(int id)
        {
            return _context.Configuraciones.Any(e => e.ConfiguracionId == id);
        }
    }
}
